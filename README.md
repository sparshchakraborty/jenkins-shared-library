# Jenkins Shared Library – Pipeline as Code

## Overview
This repository contains a **Jenkins Shared Library** that implements the **Pipeline as Code** pattern.  
It centralizes CI/CD pipeline logic so multiple application repositories can reuse the same standardized Jenkins pipeline instead of keeping duplicated Jenkinsfiles.

Benefits:
- Easier to maintain CI/CD logic
- Consistent pipelines across teams
- Faster onboarding for new repositories
- Simple to extend for deployments and integrations

---

## Problem Statement
In many Jenkins-based environments:

- Each repository contains its own Jenkinsfile  
- Pipeline logic is duplicated across projects  
- Updating pipelines requires changing multiple repositories  
- CI/CD behavior becomes inconsistent over time  

This increases maintenance cost and the risk of errors.

---

## Solution
This project solves those problems by:

- Centralizing CI/CD logic in a Jenkins Shared Library  
- Keeping application Jenkinsfiles minimal and configuration-driven  
- Reusing the same pipeline across repositories  
- Enforcing consistent CI/CD standards across teams

---

## Architecture Overview

### High-level flow
1. Developer pushes code to an application repository.  
2. Jenkins reads the repository `Jenkinsfile`.  
3. The `Jenkinsfile` references this shared library via `@Library`.  
4. Jenkins loads the pipeline logic from this repository.  
5. The shared library executes standardized CI/CD stages (checkout, build, test, docker build, deploy placeholder).

---

## How It Works (step-by-step)
1. Developer pushes code to the application repo.  
2. Jenkins triggers a job and reads the app's `Jenkinsfile`.  
3. The `Jenkinsfile` references the shared library: `@Library('devops-lib') _`.  
4. Jenkins downloads and loads the shared library from this repository.  
5. The shared pipeline runs standardized stages; behavior is controlled by parameters passed from the app repo.

---

## Technologies Used
- Jenkins  
- Jenkins Shared Libraries (Groovy)  
- Docker  
- Git & GitHub

---

## Repository Structure
A concise, wrapped representation that displays well on GitHub:

- `jenkins-shared-library/`  
  - `vars/`  
    - `cicdPipeline.groovy` — main reusable pipeline definition  
  - `src/`  
    - `org/`  
      - `devops/` — utility classes (future use)  
  - `README.md`

> If you prefer a fixed-width tree, place it inside a fenced code block. Note that code blocks may introduce horizontal scrolling on narrow screens.

---

## Shared Pipeline Details

**`vars/cicdPipeline.groovy`**
- Exposes a reusable pipeline function callable from an application `Jenkinsfile`.  
- Accepts configuration as a `Map` (e.g., repo, buildCmd, testCmd, imageName, tag).  
- Implements common CI/CD stages: checkout, build, test, docker build.  
- Contains a deploy placeholder that can be extended for Kubernetes, ECS, or other targets.

---

## Example Usage (application `Jenkinsfile`)
```groovy
@Library('devops-lib') _

cicdPipeline(
  repo: 'https://github.com/your-username/sample-python-app.git',
  buildCmd: 'echo Build successful',
  testCmd: 'echo Tests passed',
  imageName: 'pipeline-as-code',
  tag: 'v1'
)
The application Jenkinsfile contains no pipeline logic — only configuration values.

Pipeline Stages (summary)
Checkout — clone application source from GitHub

Build — run the build command provided by the application

Test — execute automated tests

Docker Image Build — build Docker image using the repository Dockerfile

Deploy — placeholder for deployment steps (extensible)

Benefits
Removes duplicated Jenkinsfiles across repos

Simplifies CI/CD maintenance and upgrades

Improves consistency and reliability of pipelines

Speeds up onboarding for new projects

Pipeline logic is version-controlled and auditable

Future Enhancements
Push Docker images to a registry (ECR / Docker Hub)

Branch-based pipeline behaviors (feature / staging / prod)

Automated deployments (Kubernetes / ECS) via the shared library

Security scanning (Trivy, Snyk) as pipeline stages

Notifications (Slack / Email) and Slack workflow integration

Environment parameterization (dev / qa / prod)

Author
Part of a DevOps portfolio demonstrating practical Jenkins Pipeline-as-Code and Shared Library design patterns.

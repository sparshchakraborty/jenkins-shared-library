# Jenkins Shared Library – Pipeline as Code

## Overview
This repository contains a **Jenkins Shared Library** that implements the **Pipeline as Code** approach.  
The purpose of this project is to centralize CI/CD pipeline logic so that multiple application repositories can reuse the same standardized Jenkins pipeline instead of maintaining duplicated Jenkinsfiles.

By moving pipeline logic into a shared library, CI/CD becomes easier to maintain, more consistent across teams, and simpler to extend over time.

---

## Problem Statement
In many Jenkins-based CI/CD environments:

- Each repository maintains its own Jenkinsfile
- Pipeline logic is duplicated across projects
- Updating pipelines requires changes in multiple repositories
- CI/CD behavior becomes inconsistent over time

This results in higher maintenance effort and increased risk of configuration errors.

---

## Solution
This project solves these problems by:

- Centralizing CI/CD logic in a Jenkins Shared Library
- Keeping application Jenkinsfiles minimal and configuration-based
- Reusing the same pipeline across multiple repositories
- Enforcing consistent CI/CD standards across projects

---

## Architecture Overview

### High-Level Flow

Developer
|
| git push
v
Application Repository
| (Jenkinsfile)
v
Jenkins Controller
| loads shared library
v
Jenkins Shared Library (this repository)
|
v
Reusable CI/CD Pipeline
├── Checkout
├── Build
├── Test
├── Docker Image Build
└── Deploy (Extensible)


---

## How It Works

1. A developer pushes code to an application repository.
2. Jenkins triggers a pipeline job and reads the Jenkinsfile.
3. The Jenkinsfile references this shared library using `@Library`.
4. Jenkins loads the pipeline logic from this repository.
5. The shared pipeline executes standardized CI/CD stages.
6. Pipeline behavior is controlled using parameters passed from the application repository.

---

## Technologies Used
- Jenkins
- Jenkins Shared Libraries
- Groovy
- Docker
- Git and GitHub

---

## Repository Structure

jenkins-shared-library/
├── vars/
│ └── cicdPipeline.groovy # Reusable pipeline definition
├── src/
│ └── org/
│ └── devops/ # Utility classes (future use)
└── README.md


---

## Shared Pipeline Details

### cicdPipeline.groovy
- Defines a reusable Jenkins pipeline
- Accepts configuration as a Map
- Implements common CI/CD stages
- Can be extended without modifying application repositories

---

## Example Usage (Application Jenkinsfile)

```groovy
@Library('devops-lib') _

cicdPipeline(
    repo: 'https://github.com/your-username/sample-python-app.git',
    buildCmd: 'echo Build successful',
    testCmd: 'echo Tests passed',
    imageName: 'pipeline-as-code',
    tag: 'v1'
)
The application Jenkinsfile contains no pipeline logic, only configuration values.

Pipeline Stages
Checkout

Clones the application source code from GitHub

Build

Executes build commands defined by the application

Test

Runs automated test commands

Docker Image Build

Builds a Docker image using the application Dockerfile

Deploy

Placeholder stage for future deployments

Benefits
Eliminates duplicated Jenkinsfiles

Simplifies CI/CD maintenance

Improves consistency across projects

Enables faster onboarding of new repositories

Pipeline logic is fully version-controlled

Future Enhancements
Push Docker images to a container registry

Branch-based pipeline execution

Deployment to Kubernetes or AWS ECS

Security scanning (Trivy, Snyk)

Notifications via Slack or Email

Environment-based deployments (dev, qa, prod)

Author
This project was created as part of a DevOps portfolio to demonstrate real-world Jenkins Pipeline as Code and Shared Library implementation.


---

## ✅ What to do now (very simple)

1. Open `README.md`
2. **Delete everything**
3. Paste the content above
4. Save
5. Run:

```bash
git add README.md
git commit -m "Rewrite README with clean architecture and formatting"
git push

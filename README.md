# Jenkins Shared Library – Pipeline as Code

## Overview
This repository contains a **Jenkins Shared Library** that implements the **Pipeline as Code** approach.  
It centralizes CI/CD pipeline logic so multiple application repositories can reuse the same standardized pipeline instead of maintaining duplicated Jenkinsfiles.

This makes pipelines easier to maintain, more consistent across teams, and simpler to extend.

---

## Problem Statement
In many Jenkins-based CI/CD environments:

- Each repository maintains its own Jenkinsfile.
- Pipeline logic gets duplicated across projects.
- Updating pipelines requires changes in multiple repositories.
- CI/CD behavior becomes inconsistent over time.

This raises maintenance costs and increases the risk of configuration errors.

---

## Solution
This project solves those problems by:

- Centralizing CI/CD logic in a Jenkins Shared Library.
- Keeping application Jenkinsfiles minimal and configuration-based.
- Reusing the same pipeline across repositories.
- Enforcing consistent CI/CD standards across teams.

---

## Architecture Overview

### High-level flow

1. Developer pushes code to an application repository.
2. Jenkins reads the repository `Jenkinsfile`.
3. The `Jenkinsfile` references this shared library via `@Library`.
4. Jenkins loads pipeline logic from this repository.
5. The shared pipeline executes standardized CI/CD stages.

---

## How It Works (step-by-step)

1. Developer pushes code to the app repo.  
2. Jenkins triggers a job and reads the app's `Jenkinsfile`.  
3. `Jenkinsfile` references the shared library: `@Library('devops-lib') _`.  
4. Jenkins downloads the shared library from this repository.  
5. The shared pipeline runs standardized stages; behavior is controlled via parameters from the app repo.

---

## Technologies Used
- Jenkins  
- Jenkins Shared Libraries  
- Groovy  
- Docker  
- Git & GitHub

---

## Repository Structure

Use this **nested list** representation (wraps nicely on narrow screens):

- `jenkins-shared-library/`
  - `vars/`
    - `cicdPipeline.groovy` — main reusable pipeline definition
  - `src/`
    - `org/`
      - `devops/` — utility classes (future use)
  - `README.md`

> *Note:* If you prefer an ASCII tree, use a fenced code block. That preserves spacing but will introduce horizontal scrolling on small viewports.

---

## Shared Pipeline Details

**`vars/cicdPipeline.groovy`**
- Defines a reusable Jenkins pipeline function
- Accepts configuration as a `Map`
- Implements common CI/CD stages (checkout, build, test, docker build)
- Extensible for deployment stages (Kubernetes, ECS, etc.)

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


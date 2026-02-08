# Jenkins Shared Library – Pipeline as Code

## Overview
This repository contains a **Jenkins Shared Library** that implements the **Pipeline as Code** approach.  
The goal of this project is to centralize CI/CD pipeline logic so that multiple application repositories can reuse the same standardized pipeline instead of maintaining duplicate Jenkinsfiles.

By moving pipeline logic into a shared library, CI/CD becomes easier to maintain, more consistent across teams, and simpler to extend.

---

## Problem Statement
In many Jenkins-based environments:

- Each repository contains its own Jenkinsfile
- Pipeline logic is duplicated across projects
- Updating pipelines requires changes in multiple repositories
- CI/CD behavior becomes inconsistent over time

This increases maintenance effort and the risk of configuration errors.

---

## Solution
This project solves the above problems by:

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
Application Repository (Jenkinsfile)
|
| @Library reference
v
Jenkins Controller
|
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
6. Pipeline behavior is controlled using parameters passed from the application.

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

### `vars/cicdPipeline.groovy`
- Defines a reusable Jenkins pipeline
- Accepts configuration as a `Map`
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

# Jenkins Shared Library – Pipeline as Code

## Overview
Reusable Jenkins Shared Library to standardize CI/CD pipelines across multiple repositories.

## Features
- Centralized pipeline logic
- Build, test, and Docker stages
- Easy to extend for deployments
- Reduces Jenkinsfile duplication

## Usage
```groovy
@Library('devops-lib') _
cicdPipeline(...)


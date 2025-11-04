# SE333_Assignment_5

[![SE333_CI](https://github.com/ALGeek01/SE333_Assignment_5/actions/workflows/SE333_CI.yml/badge.svg)](https://github.com/ALGeek01/SE333_Assignment_5/actions/workflows/SE333_CI.yml)

## About
Assignment 5 for Software Engineering - implementing unit tests, mocking tests, and integration tests for the Amazon and Barnes & Noble systems.

## What's Included
- Unit tests for both Amazon and Barnes packages (7 test classes)
- Mocking tests using Mockito (2 test classes)
- Integration tests with HSQLDB (1 test class)
- 54 total tests

## CI/CD Setup
The GitHub Actions workflow runs on push and does:
- Checkstyle validation
- Maven test execution
- JaCoCo coverage reports
- Uploads artifacts for review

## Status
All tests passing. GitHub Actions workflow completes successfully with static analysis, tests, and coverage.

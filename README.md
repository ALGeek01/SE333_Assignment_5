# SE333_Assignment_5

[![SE333_CI](https://github.com/ALGeek01/SE333_Assignment_5/actions/workflows/SE333_CI.yml/badge.svg)](https://github.com/ALGeek01/SE333_Assignment_5/actions/workflows/SE333_CI.yml)

## Project Overview
This repository contains comprehensive unit, mocking, and integration tests for Assignment 5 of the Software Engineering course. The project implements automated testing for both Amazon and Barnes & Noble e-commerce systems.

## Testing Coverage
- **Unit Tests**: 7 test classes covering Amazon and Barnes packages
- **Mocking Tests**: 2 test classes using Mockito framework
- **Integration Tests**: 1 test class with HSQLDB database
- **Total Tests**: 54 tests, all passing ✓

## CI/CD Pipeline
The GitHub Actions workflow automatically runs on every push to main and includes:
- Code style checking with Checkstyle
- Automated test execution with Maven
- Code coverage analysis with JaCoCo
- Artifact uploads for reports

## Build Status
✅ **All GitHub Actions checks are passing** - The workflow successfully completes static analysis, testing, and coverage reporting.

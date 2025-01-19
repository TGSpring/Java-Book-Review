# Chapter 3: Methods and Calculations

## Overview

This repository covers the key concepts from Chapter 3 of *Building Java Programs: A Back to Basics Approach* by Stuart Reges. Chapter 3 introduces the mechanics of parameters, method overloading, and defining methods that return values. We also work with the `Math` class and explore string objects, interactive programs, and the Scanner class to handle user input.

Additionally, we dive into a practical case study involving the calculation of projectile motion, demonstrating how to break down complex calculations into reusable, modular methods.

## Sections Covered

### 3.1: Mechanics of Parameters and Method Overloading

- **Parameters**: Understanding how parameters work in methods and their limitations.
- **Multiple Parameters**: Using more than one parameter in a method, and how parameters relate to constants.
- **Overloading Methods**: How to define multiple methods with the same name but different parameters.

### 3.2: The Math Class and Defining Methods That Return Values

- **Math Class**: Using `Math` class methods for common mathematical operations such as trigonometry, exponentiation, and more.
- **Methods that Return Values**: Learn how to define methods that return results (e.g., time of flight, range, and maximum height).

### 3.3: String Objects and Interactive Programs

- **String Objects**: Working with string manipulation and concatenation in Java.
- **Interactive Programs and Scanner**: Using the `Scanner` class to gather input from users and interact with the program.

### 3.4: Projectile Motion Case Study

In this section, we calculated the time of flight, maximum height, and range of a projectile launched at an initial velocity and launch angle. The case study demonstrates how to structure a real-world application into modular, reusable methods.

### Key Methods:

- `getInput`: Collects user input for velocity and angle.
- `calcTimeOfFlight`: Calculates the time the projectile stays in the air.
- `calcHeight`: Determines the maximum height the projectile reaches.
- `calcRange`: Computes the horizontal range of the projectile.
- `displayResults`: Prints out the results of the calculations.
- `askToCont`: Prompts the user if they want to simulate another projectile.

## Case Study: Projectile Motion

The **Projectile Motion** case study calculates and displays the time of flight, maximum height, and range of a projectile given its initial velocity and launch angle. It uses the following physics equations:

- **Time of Flight**:
  \[
  T = \frac{2v \sin(\theta)}{g}
  \]
  
- **Maximum Height**:
  \[
  H = \frac{v^2 \sin^2(\theta)}{2g}
  \]

- **Range**:
  \[
  R = \frac{v^2 \sin(2\theta)}{g}
  \]

Where:
- \( v \) is the initial velocity
- \( \theta \) is the launch angle in radians
- \( g \) is the acceleration due to gravity (9.8 m/s²)

You can run the program interactively, inputting values for velocity and angle to simulate different projectile motions.

## Running the Code

1. Clone the repository to your local machine.
2. Navigate to the `Chapter 3` directory.
3. Compile the Java files:
   ```bash
   javac caseStudy.java

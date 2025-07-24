# LeetCode Solutions

A production-ready, multi-language repository containing well-documented LeetCode solutions with standardized code patterns, comprehensive testing, and optimized algorithms across Java, Python, and C#.

## Overview

This repository serves as a comprehensive reference for technical interview preparation and algorithmic problem-solving. Solutions are organized by difficulty, category, and company-specific patterns, with emphasis on code quality, maintainability, and multiple algorithmic approaches.

## Repository Structure

```
├── java/                           # Primary Java solutions with full standardization
│   ├── solutions/
│   │   ├── neetcode_150/          # Curated essential problems for interviews
│   │   ├── top_interview_questions/ # Most frequently asked problems
│   │   ├── company_wise/          # Company-specific problem patterns
│   │   └── pareto_problem_set/    # High-impact problem subset
│   ├── common/                    # Shared utilities and data structures
│   │   ├── Solution.java          # Standard interface for all solutions
│   │   ├── TreeNode.java          # Binary tree implementations
│   │   ├── ListNode.java          # Linked list structures
│   │   └── *Utils.java            # Helper utilities for common operations
│   └── Main.java                  # Test harness and solution runner
├── python/                        # Python implementations
│   ├── easy/                      # Easy difficulty solutions
│   ├── medium/                    # Medium difficulty solutions
│   ├── main.py                    # Python test runner
│   └── solution_base.py           # Base classes and utilities
├── csharp/                        # C# implementations
│   └── solutions/                 # Organized C# solution files
└── .vscode/                       # Development environment configuration
```

## Solution Standards

### Java Implementation Standards

All Java solutions follow a consistent pattern for maintainability and readability:

**File Naming Convention:**

```
LC_{problem_number}_{ProblemNameInCamelCase}.java
```

**Class Structure:**

```java
/**
 * LeetCode {number}: {Problem Title}
 * URL: https://leetcode.com/problems/{problem-slug}/
 * Difficulty: {Easy|Medium|Hard}
 *
 * Problem Description:
 * {Brief problem description}
 *
 * Approach 1: {Algorithm Name}
 * - {High-level approach explanation}
 * - Time Complexity: O(...)
 * - Space Complexity: O(...)
 *
 * Approach 2: {Alternative Algorithm} (if applicable)
 * - {Alternative approach explanation}
 * - Time Complexity: O(...)
 * - Space Complexity: O(...)
 */
public class LC_{number}_{ProblemName} implements Solution {

    public ReturnType algorithmNameOptimal(Parameters...) {
        // Optimized implementation
    }

    public ReturnType algorithmNameBruteForce(Parameters...) {
        // Brute force implementation for comparison
    }

    @Override
    public void run() {
        // Comprehensive test cases with edge cases
    }
}
```

### Key Features

- **Multiple Approaches**: Each problem includes 2-3 different algorithmic approaches when applicable
- **Descriptive Naming**: Method names clearly indicate the algorithmic approach (`twoSumHashMap`, `binarySearchRecursive`)
- **Comprehensive Testing**: All approaches validated with multiple test cases including edge cases
- **Complexity Analysis**: Detailed time and space complexity analysis for each approach
- **Interface Compliance**: All solutions implement the `Solution` interface for consistent testing

## Development Setup

### Java Environment

**Prerequisites:**

- JDK 21+ (configured for compatibility)
- VS Code with Java Extension Pack or IntelliJ IDEA

**Build and Run:**

```bash
cd java
javac Main.java
java Main
```

**Testing Specific Solutions:**
Modify the solution instantiation in `Main.java`:

```java
Solution solution = new LC_1_TwoSum(); // Replace with desired solution
```

### Python Environment

**Prerequisites:**

- Python 3.8+
- Virtual environment (included in repository)

**Setup:**

```bash
cd python
source .venv/bin/activate    # Unix/macOS
# .venv\Scripts\activate     # Windows
python main.py
```

### C# Environment

**Prerequisites:**

- .NET 5+ or .NET Core 3.1+
- Visual Studio, VS Code with C# extension, or JetBrains Rider

**Execution:**

```bash
cd csharp/solutions/{category}/{difficulty}
dotnet run SolutionFile.cs
```

## Contributing

### Adding New Solutions

1. **Choose appropriate directory** based on problem category and difficulty
2. **Follow naming conventions** as specified above
3. **Implement the `Solution` interface** (Java) or follow established patterns (Python/C#)
4. **Include multiple approaches** when different algorithmic strategies exist
5. **Add comprehensive test cases** covering normal cases, edge cases, and boundary conditions
6. **Document time and space complexity** for each approach

### Code Quality Standards

- **Readable variable names** that clearly indicate purpose
- **Comprehensive comments** explaining complex algorithmic steps
- **Consistent formatting** following language-specific conventions
- **Error handling** for edge cases and invalid inputs
- **Performance considerations** documented in complexity analysis

## Problem Categories

**NeetCode 150**: Essential problems covering fundamental algorithms and data structures commonly encountered in technical interviews.

**Top Interview Questions**: Most frequently asked problems across major technology companies, organized by difficulty level.

**Company-Specific**: Solutions organized by company interview patterns, including Amazon (scale and leadership principles), Google (algorithmic optimization), and Goldman Sachs (financial sector patterns).

**Pareto Problem Set**: High-impact problems that provide maximum interview preparation value with focused study effort.

## Development Configuration

The repository includes pre-configured development settings optimized for Java development while supporting multi-language work:

- **VS Code Configuration**: Java-focused settings with proper exclusions for other languages
- **Build System**: Configured for Java compilation with appropriate classpath management
- **Git Configuration**: Excludes build artifacts and IDE-specific files while preserving essential configuration
- **Multi-language Support**: Isolated environments for Java, Python, and C# development

This repository is designed for long-term maintenance and collaborative development, providing a solid foundation for technical interview preparation and algorithmic study.

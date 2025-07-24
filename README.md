# LeetCode Solutions Repository

This repository contains LeetCode solutions organized by programming language for long-term maintenance.

## Project Structure

```
LeetCode/
├── java/                    # Java solutions and utilities
│   ├── solutions/          # Organized Java solution files
│   ├── common/            # Common Java utilities and interfaces
│   └── Main.java          # Main runner for testing Java solutions
├── python/                # Python solutions and utilities
│   ├── easy/             # Easy difficulty Python solutions
│   ├── medium/           # Medium difficulty Python solutions
│   ├── .venv/            # Python virtual environment
│   ├── main.py           # Main Python runner
│   └── solution_base.py  # Base classes for Python solutions
├── csharp/               # C# solutions
│   └── solutions/        # Organized C# solution files
└── .vscode/              # VS Code configuration (Java-focused)
```

## Setup

### Java

- **JDK**: Java 23 (configured in VS Code settings)
- **IDE**: VS Code with Java extension pack or IntelliJ IDEA
- **Entry Point**: `java/Main.java`

To run Java solutions:

```bash
cd java
javac Main.java
java Main
```

### Python

- **Version**: Python 3.x
- **Virtual Environment**: Located in `python/.venv/`
- **Entry Point**: `python/main.py`

To run Python solutions:

```bash
cd python
source .venv/bin/activate  # On macOS/Linux
# .venv\Scripts\activate   # On Windows
python main.py
```

### C#

- **Runtime**: .NET Core/.NET 5+
- **IDE**: Visual Studio, VS Code with C# extension, or Rider
- **Entry Point**: Individual .cs files can be compiled and run separately

To run C# solutions:

```bash
cd csharp/solutions/top_interview_questions/easy
dotnet run <filename>.cs  # or compile individually
```

## Adding New Solutions

### Java Solutions

1. Create your solution class in the appropriate directory under `java/solutions/`
2. Implement the `Solution` interface from `common.Solution`
3. Add a `run()` method to demonstrate your solution
4. Update `java/Main.java` to test your new solution

### Python Solutions

1. Create your solution file in `python/easy/` or `python/medium/`
2. Follow the pattern established in existing Python solutions
3. Update `python/main.py` to test your new solution

### C# Solutions

1. Create your solution file in the appropriate directory under `csharp/solutions/`
2. Follow C# naming conventions and structure
3. Compile and test individually as needed

## Configuration Notes

- VS Code is configured to use the `java/` directory as the main source path
- Python virtual environment is excluded from Java project scanning
- All three languages (Java, Python, C#) can coexist without interference
- Git ignores compiled files and IDE-specific configurations
- Each language has its own dedicated directory structure

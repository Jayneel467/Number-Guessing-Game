# Number Guessing Game

A simple guessing game built in Python and Java. The player chooses a number range and tries to guess the hidden number before running out of attempts.

## Features
- Random number generation within a custom range
- Guess feedback such as "too high" or "too low"
- Limited number of attempts
- Python and Java implementations
- Basic input validation

## Project Files
- `Number_game.py` - Python version
- `Number_game.java` - Java version
- `test_number_game.py` - Python tests for logic validation

## How to Run the Python Version

```bash
python Number_game.py
```

## How to Run the Java Version

```bash
javac Number_game.java
java Number_game
```

## Example Gameplay

```text
Welcome to the number guessing game!
You have 10 chances to guess the number.
Let's start the game!
Enter the lower limit of the range: 1
Enter the upper limit of the range: 20
You have 10 chances to guess the number from 1 to 20.
Guess the number: 10
The number is too high! Try lower.
```

## Testing

For the Python version:

```bash
python -m pytest -q
```

## License
This project is for educational and portfolio use.

# Keys Game — Java Swing

A typing reaction game built with Java Swing. A random letter appears on screen and you must press the matching key before the timer runs out. Miss too many and you lose energy — reach zero and the game ends.

![Game screenshot]()

---

## Architecture

The project follows a clean separation of concerns across five classes:

```
Main
 ├── creates  GameState
 ├── creates  GameWindow(GameState)
 └── starts   GameLoop(GameState, GameWindow)
```

| Class | Layer | Responsibility |
|-------|-------|----------------|
| `Main.java` | Entry point | Wires together state, window, and game loop. No logic. |
| `GameState.java` | Model | All mutable game variables: score, energy, speed, key pressed, current letter. No UI dependency. |
| `GameWindow.java` | View | Builds and owns every Swing component. Reads `GameState` only for the key listener. |
| `GameLoop.java` | Controller | Background thread that drives the game: generates letters, counts down, evaluates input, updates `GameWindow` and `GameState`. |
| `GameColors.java` | Constants | Centralized color palette referenced by both `GameWindow` and `GameLoop`. |

---

## How to Run

### Prerequisites

- Java 8 or higher — verify with `java -version`
- If not installed: https://www.java.com/download/

### macOS / Linux

```bash
# Compile all source files
javac *.java

# Run
java Main
```

### Windows — Command Prompt

```cmd
:: Compile
javac *.java

:: Run
java Main
```

### Windows — PowerShell

```powershell
# Compile
javac *.java

# Run
java Main
```

---

## Gameplay

| Event | Result |
|-------|--------|
| Correct key typed | +10 points, green flash, ✓ icon |
| Wrong key / timeout | −5 energy, red flash, ✗ icon |
| Every 100 points | Level up — timer speeds up |
| Energy reaches 0 | Game over, final score displayed |

Maximum level: **5** (200 ms per letter).

---

## Project Structure

```
├── Main.java         # Entry point
├── GameState.java    # Data model
├── GameWindow.java   # Swing UI / View
├── GameLoop.java     # Game logic thread
├── GameColors.java   # Color palette constants
└── README.md
```

---

###### Contributions are welcome

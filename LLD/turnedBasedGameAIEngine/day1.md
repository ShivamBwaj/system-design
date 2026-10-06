## requrements : takeing a game and having the ability to play it through AI

think abt 
what kind of apis should u be exposing in the backend?
BOARD
-start
-move
-obj isComplete giving state of game

## after writing code
-too much of repetiton of code
like for loops
-all objs are in one place

## Optimizations attempted

### 1. Early exit on a mismatch

When checking a row, column, or diagonal, the loop stops as soon as a cell does
not match the first cell in that line.

avoids checking the remaining cells after the line is already known to be
incomplete.

### 2. Early exit after finding a winning line

After each row or column is checked, the outer loop stops when a complete line
is found. `isComplete` then immediately returns a winning `GameResult` instead
of checking the other rows, columns, and diagonals.

This reduces unnecessary work and makes the common "game has been won" path
finish quickly.

### 3. Avoiding duplicate checks inside a line

For rows and columns, the first cell is stored in `firstCharacter`, so the
inner loop starts at index `1` instead of comparing the first cell with itself.

### 4. Checking game state in a short-circuit order

The method checks in this order:

1. Rows
2. Columns
3. Main diagonal
4. Reverse diagonal
5. Tie condition

As soon as a winner or tie is identified, the method returns. This prevents
later checks from running unnecessarily.

## Important correctness notes

- The current line checks call `.equals(...)` on cells that may be `null`, so an
	unfinished board can throw a `NullPointerException`. Empty cells should be
	handled before comparing values.
- The filled-cell counter currently starts the inner loop at `j = 1` and checks
	`cells[j][i]`. It skips part of the board and does not count all nine cells,
	so the tie check is incorrect.
- The diagonal loops break on the first mismatch, but the `diagComplete` and
	`revDiagComplete` flags should be initialized once per diagonal check rather
	than reset on every iteration.
- The row, column, and diagonal logic is repetitive. A future optimization for
	maintainability would be to represent all winning lines uniformly and check
	them through one reusable helper.



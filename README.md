# Horus-recrutacja-JJD-09-2025

This is my solution for the Java Developer recruitment task at Horus.  
The goal was to implement methods in `FileCabinet` to work with folders, including nested ones, through the `MultiFolder` interface.

## Implemented Methods

- `findFolderByName(String name)` – returns a folder by its name (Optional).  
- `findFoldersBySize(String size)` – returns all folders of the given size.  
- `count()` – returns the total number of folders, including those inside `MultiFolder`s.

All logic is inside `FileCabinet`, using a single recursive method `walk` to avoid code duplication.

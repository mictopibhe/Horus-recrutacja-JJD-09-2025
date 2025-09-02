package pl.davidduke;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileCabinetTest {

    private FileCabinet getFileCabinet() {
        Folder f1 = new Folder() {
            @Override
            public String getName() {
                return "folder1";
            }

            @Override
            public String getSize() {
                return "small";
            }
        };
        Folder f2 = new Folder() {
            @Override
            public String getName() {
                return "folder2";
            }

            @Override
            public String getSize() {
                return "small";
            }
        };
        Folder f3 = new Folder() {
            @Override
            public String getName() {
                return "folder3";
            }

            @Override
            public String getSize() {
                return "medium";
            }
        };
        MultiFolder f4 = new MultiFolder() {
            @Override
            public String getName() {
                return "folder4";
            }

            @Override
            public String getSize() {
                return "large";
            }

            @Override
            public List<Folder> getFolders() {
                return List.of(f2, f3);
            }
        };
        return new FileCabinet(List.of(f1, f4));
    }

    @Test
    void findFolderByNameShouldReturnFolderWithSpecifiedName() {
        FileCabinet cabinet = getFileCabinet();
        Folder f1 = cabinet.findFolderByName("folder1").orElseThrow();
        Folder f2 = cabinet.findFolderByName("folder2").orElseThrow();
        Folder f3 = cabinet.findFolderByName("folder3").orElseThrow();
        Folder f4 = cabinet.findFolderByName("folder4").orElseThrow();
        assertEquals("folder1", f1.getName());
        assertEquals("folder2", f2.getName());
        assertEquals("folder3", f3.getName());
        assertEquals("folder4", f4.getName());
    }

    @Test
    void findFoldersBySizeShouldReturnFoldersListOfGivenSize() {
        FileCabinet cabinet = getFileCabinet();
        assertEquals(2, cabinet.findFoldersBySize("small").size());
        assertEquals(1, cabinet.findFoldersBySize("medium").size());
        assertEquals(1, cabinet.findFoldersBySize("large").size());
        assertEquals("medium", cabinet.findFoldersBySize("medium").getFirst().getSize());
        assertEquals("large", cabinet.findFoldersBySize("large").getFirst().getSize());
        assertEquals("small", cabinet.findFoldersBySize("small").getFirst().getSize());
        assertEquals("small", cabinet.findFoldersBySize("small").getLast().getSize());
    }

    @Test
    void countShouldReturnCorrectFoldersCount() {
        FileCabinet cabinet = getFileCabinet();
        assertEquals(4, cabinet.count());
    }
}
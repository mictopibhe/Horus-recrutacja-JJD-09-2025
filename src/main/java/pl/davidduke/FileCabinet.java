package pl.davidduke;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class FileCabinet  implements Cabinet{
    private final List<Folder> folders;

    public FileCabinet(List<Folder> folders) {
        this.folders = folders;
    }
    @Override
    public Optional<Folder> findFolderByName(String name) {
        return walk(this.folders).filter(f -> f.getName().equals(name)).findFirst();
    }

    @Override
    public List<Folder> findFoldersBySize(String size) {
        return walk(this.folders).filter(f -> f.getSize().equalsIgnoreCase(size)).toList();
    }

    @Override
    public int count() {
        return Math.toIntExact(walk(this.folders).count());
    }

    private Stream<Folder> walk(List<Folder> folders) {
        return folders.stream().flatMap(folder -> {
            if (folder instanceof MultiFolder multiFolder) {
                return Stream.concat(Stream.of(folder), walk(multiFolder.getFolders()));
            }
            return Stream.of(folder);
        });
    }
}

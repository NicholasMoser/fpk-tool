package com.github.nicholasmoser.gnt4;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import com.github.nicholasmoser.GNTFileProtos.GNTFile;
import com.github.nicholasmoser.GNTFileProtos.GNTFiles;
import com.github.nicholasmoser.Workspace;

/**
 * A Workspace for GNT4 decompressed files.
 */
public class GNT4Workspace implements Workspace {

  private Path directory;
  
  private Path root;
  
  private Path uncompressed;
  
  private Path workspaceState;
  
  private GNT4Files gnt4Files;
  
  private boolean isDirty;

  /**
   * @param directory The directory of GNT4 decompressed files.
   */
  public GNT4Workspace(Path directory) {
    this.directory = directory;
    this.root = directory.resolve(GNT4Files.ROOT_DIRECTORY);
    this.uncompressed = directory.resolve(GNT4Files.UNCOMPRESSED_DIRECTORY);
    this.workspaceState = directory.resolve(GNT4Files.WORKSPACE_STATE);
    this.gnt4Files = new GNT4Files(uncompressed, workspaceState);
    this.isDirty = false;
  }

  @Override
  public Path getWorkspaceDirectory() {
    return directory;
  }

  @Override
  public Path getRootDirectory() {
    return root;
  }

  @Override
  public Path getUncompressedDirectory() {
    return uncompressed;
  }

  @Override
  public Path getWorkspaceState() {
    return workspaceState;
  }
  
  @Override
  public void initState() throws IOException {
    gnt4Files.initState();
  }
  
  @Override
  public void loadExistingState() throws IOException {
    gnt4Files.loadExistingState();
  }

  @Override
  public void updateState() throws IOException {
    gnt4Files.updateState();
  }

  @Override
  public void setDirty(boolean isDirty) {
    this.isDirty = isDirty;
  }

  @Override
  public boolean isDirty() {
    return isDirty;
  }

  @Override
  public Set<GNTFile> getMissingFiles(GNTFiles newGntFiles) {
    return gnt4Files.getMissingFiles(newGntFiles);
  }

  @Override
  public Set<GNTFile> getChangedFiles(GNTFiles newGntFiles) {
    return gnt4Files.getChangedFiles(newGntFiles);
  }
}

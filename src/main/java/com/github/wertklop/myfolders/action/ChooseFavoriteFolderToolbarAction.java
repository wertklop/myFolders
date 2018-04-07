/*
 *  Copyright 2018 Gennadii Kurbatov <wertklop@yandex.ru>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.github.wertklop.myfolders.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileChooser.FileElement;
import com.intellij.openapi.fileChooser.FileSystemTree;
import com.intellij.openapi.fileChooser.actions.FileChooserAction;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.File;

import static com.intellij.icons.AllIcons.Nodes.Folder;

public class ChooseFavoriteFolderToolbarAction extends FileChooserAction {

    private String path;

    public ChooseFavoriteFolderToolbarAction(String path) {
        super(path, null, Folder);
        this.path = path;
    }

    @Override
    protected void update(FileSystemTree fileSystemTree, AnActionEvent event) {
    }

    @Override
    protected void actionPerformed(FileSystemTree fileSystemTree, AnActionEvent event) {
        VirtualFile file = LocalFileSystem.getInstance().findFileByIoFile(new File(path));
        if (FileElement.isFileHidden(file)) {
            fileSystemTree.showHiddens(true);
        }
        fileSystemTree.select(file, () -> fileSystemTree.expand(file, null));
    }
}
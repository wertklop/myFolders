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

import com.github.wertklop.myfolders.service.FavoriteContainerApplicationService;
import com.github.wertklop.myfolders.support.MessagesBundle;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.fileChooser.FileSystemTree;
import com.intellij.openapi.fileChooser.actions.FileChooserAction;
import com.intellij.openapi.vfs.VirtualFile;

import java.util.Collection;

import static com.intellij.icons.AllIcons.General.AddFavoritesList;
import static com.intellij.icons.AllIcons.ToolbarDecorator.Remove;

public class AddRemoveFavoriteFolderContextMenuAction extends FileChooserAction {

    private static final String ADD_TITLE = MessagesBundle.getMessage("dialog.add.favorite.title");
    private static final String REMOVE_TITLE = MessagesBundle.getMessage("dialog.remove.favorite.title");

    @Override
    protected void update(FileSystemTree fileSystemTree, AnActionEvent event) {
        Presentation presentation = event.getPresentation();
        VirtualFile selectedFile = fileSystemTree.getSelectedFile();
        if (event.isFromActionToolbar() || selectedFile == null || !selectedFile.isDirectory()) {
            presentation.setVisible(false);
            return;
        }

        String path = selectedFile.getPath();
        if (getApplicationFavorites().contains(path)) {
            presentation.setText(REMOVE_TITLE);
            presentation.setIcon(Remove);
        } else {
            presentation.setText(ADD_TITLE);
            presentation.setIcon(AddFavoritesList);
        }

        presentation.setVisible(fileSystemTree.selectionExists());
    }

    @Override
    protected void actionPerformed(FileSystemTree fileSystemTree, AnActionEvent event) {
        String path = fileSystemTree.getSelectedFile().getPath();
        if (getApplicationFavorites().contains(path)) {
            getApplicationFavorites().remove(path);
        } else {
            getApplicationFavorites().add(path);
        }
    }

    private Collection<String> getApplicationFavorites() {
        return FavoriteContainerApplicationService.getInstance().getFavorites();
    }
}

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
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.actionSystem.Presentation;

import java.util.Collection;

import static com.intellij.icons.AllIcons.Modules.GeneratedFolder;
import static com.intellij.openapi.actionSystem.ActionPlaces.UNKNOWN;

public class ChooseFavoriteFolderToolbarActionGroup extends DefaultActionGroup {

    private int favoritesState;

    public ChooseFavoriteFolderToolbarActionGroup() {
        super("List favorites folders", true);
        getTemplatePresentation().setIcon(GeneratedFolder);
        getApplicationFavorites().forEach(path -> add(new ChooseFavoriteFolderToolbarAction(path)));
        favoritesState = getApplicationFavorites().hashCode();
    }

    @Override
    public void update(AnActionEvent event) {
        Presentation presentation = event.getPresentation();
        if (getApplicationFavorites().size() != getChildrenCount()) {
            refreshChooseActions();
        }

        if ((event.isFromContextMenu() && UNKNOWN.equals(event.getPlace())) || getApplicationFavorites().isEmpty()) {
            presentation.setVisible(false);
            return;
        }

        presentation.setVisible(true);
    }

    private void refreshChooseActions() {
        int newFavoritesState = getApplicationFavorites().hashCode();
        if (favoritesState != newFavoritesState) {
            removeAll();
            getApplicationFavorites().forEach(path -> add(new ChooseFavoriteFolderToolbarAction(path)));
            favoritesState = newFavoritesState;
        }
    }

    private Collection<String> getApplicationFavorites() {
        return FavoriteContainerApplicationService.getInstance().getFavorites();
    }
}

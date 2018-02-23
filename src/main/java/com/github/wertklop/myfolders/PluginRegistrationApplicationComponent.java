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
package com.github.wertklop.myfolders;

import com.github.wertklop.myfolders.action.AddRemoveFavoriteFolderContextMenuAction;
import com.github.wertklop.myfolders.action.ChooseFavoriteFolderToolbarActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.components.ApplicationComponent;

public class PluginRegistrationApplicationComponent implements ApplicationComponent {

    @Override
    public void initComponent() {
        ActionManager actionManager = ActionManager.getInstance();

        registerAddRemoveFolderContextMenuAction(actionManager);
        registerFolderChooseToolbarActionGroup(actionManager);
    }

    private void registerAddRemoveFolderContextMenuAction(ActionManager actionManager) {
        AddRemoveFavoriteFolderContextMenuAction action = new AddRemoveFavoriteFolderContextMenuAction();
        DefaultActionGroup group = (DefaultActionGroup) actionManager.getAction("FileChooserToolbar");
        group.add(action);
    }

    private void registerFolderChooseToolbarActionGroup(ActionManager actionManager) {
        ChooseFavoriteFolderToolbarActionGroup action = new ChooseFavoriteFolderToolbarActionGroup();
        DefaultActionGroup group = (DefaultActionGroup) actionManager.getAction("FileChooserToolbar");
        group.add(action);
    }
}


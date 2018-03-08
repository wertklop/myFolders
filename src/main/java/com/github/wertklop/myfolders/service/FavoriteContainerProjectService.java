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
package com.github.wertklop.myfolders.service;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;

@State(name = "FavoriteContainerProjectService", storages = @Storage("$PROJECT_CONFIG_DIR$/favoriteFolders.xml"))
public class FavoriteContainerProjectService implements FavoriteStateContainer {

    private Collection<String> favorites = new HashSet<>();

    public static FavoriteContainerProjectService getInstance(@NotNull Project project) {
        return ServiceManager.getService(project, FavoriteContainerProjectService.class);
    }

    @Override
    public Collection<String> getFavorites() {
        return favorites;
    }
}

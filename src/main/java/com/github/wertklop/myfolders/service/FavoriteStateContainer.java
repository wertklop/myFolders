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

import com.intellij.openapi.components.PersistentStateComponent;
import org.jdom.Element;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public interface FavoriteStateContainer extends PersistentStateComponent<Element> {

    String URL_ATTRIBUTE = "url";
    String FOLDER_ELEMENT = "folder";

    Collection<String> getFavorites();

    @Nullable
    @Override
    default Element getState() {
        Element root = new Element("favorites");

        for (String favorite : getFavorites()) {
            Element e = new Element(FOLDER_ELEMENT);
            e.setAttribute(URL_ATTRIBUTE, favorite);
            root.addContent(e);
        }

        return root;
    }

    @Override
    default void loadState(Element state) {
        Set<String> favoritesFromStore = state.getChildren(FOLDER_ELEMENT)
                                              .stream()
                                              .map(element -> element.getAttributeValue(URL_ATTRIBUTE))
                                              .collect(toSet());
        getFavorites().addAll(favoritesFromStore);
    }
}

package com.rschao.plugins.showdowncore.showdownCore.gui.recipe;

import java.util.HashMap;
import java.util.Map;

public class ActionRegistry {
    private static final Map<String, RecipeAction> actions = new HashMap<>();

    public static void register(String id, RecipeAction action) {
        actions.put(id, action);
    }

    public static RecipeAction get(String id) {
        return actions.get(id);
    }
}

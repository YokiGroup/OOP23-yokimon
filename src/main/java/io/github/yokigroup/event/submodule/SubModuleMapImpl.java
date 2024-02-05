package io.github.yokigroup.event.submodule;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class SubModuleMapImpl implements SubModuleMap{
    private final Map<Class<? extends Submodule>, Submodule> submodules = new HashMap<>();

    @Override
    public <T extends Submodule> boolean registerSubmodule(T s) {
        return submodules.put(s.getClass(), s) == null;
    }

    @Override
    public <T extends Submodule> Optional<T> getSubmodule(Class<T> type) {
        //noinspection unchecked
        T submodule = (T) submodules.get(type);
        return submodule != null ? Optional.of(submodule) : Optional.empty();
    }

    public <T extends Submodule> Set<T> subModuleSet(){
        //noinspection unchecked
        return (Set<T>) submodules.entrySet().stream()
                .map( en -> en.getKey().cast(en.getClass()) )
                .collect(Collectors.toSet());
    }
}

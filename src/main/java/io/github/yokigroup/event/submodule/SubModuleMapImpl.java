package io.github.yokigroup.event.submodule;

import java.util.*;
import java.util.stream.Collectors;

public class SubModuleMapImpl implements SubModuleMap{
    private final Map<Class<? extends Submodule>, Submodule> submodules = new HashMap<>();

    @Override
    public <T extends Submodule> boolean register(T s) {
        return submodules.put(s.getClass(), s) == null;
    }

    @Override
    public <T extends Submodule> void registerAll(Set<T> sSet) {
        sSet.forEach(this::register);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Submodule> Optional<T> get(Class<T> type) {
        Submodule tempSub = submodules.get(type);
        T retSub = null;
        if(type.isInstance(tempSub)){
            retSub = (T)tempSub;
        }
        return Optional.ofNullable(retSub);
    }

    public Set<Submodule> subModuleSet(){
        return new HashSet<>(submodules.values());
    }
}

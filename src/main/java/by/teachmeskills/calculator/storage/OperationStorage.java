package by.teachmeskills.calculator.storage;

import by.teachmeskills.calculator.entity.Operation;
import lombok.NonNull;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class OperationStorage {
    List<Operation> storage;

    public OperationStorage() {
        storage = new ArrayList<>();
    }

    public boolean offer(@NonNull Operation operation) {
        return storage.add(operation);
    }

    public List<Operation> getAll() {
        return new ArrayList<>(storage);
    }
}
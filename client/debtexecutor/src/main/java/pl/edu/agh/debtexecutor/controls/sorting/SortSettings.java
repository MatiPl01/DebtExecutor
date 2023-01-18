package pl.edu.agh.debtexecutor.controls.sorting;

public record SortSettings(
        String sortByName,
        String sortByProp,
        SortValueType sortValueType
) {}

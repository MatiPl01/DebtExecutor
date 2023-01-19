package pl.edu.agh.debtexecutor.utils;

import javafx.scene.Node;

public record FXMLWithController<T extends Node, C>(T node, C controller) {}

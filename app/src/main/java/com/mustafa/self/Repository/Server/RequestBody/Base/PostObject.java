package com.mustafa.self.Repository.Server.RequestBody.Base;

public class PostObject {
    public PostObject(Value value) {
        Value = value;
    }

    Value Value;

    public Value getValue() { 
        return Value;
    }

    public void setValue(Value value) {
        Value = value;
    }
}

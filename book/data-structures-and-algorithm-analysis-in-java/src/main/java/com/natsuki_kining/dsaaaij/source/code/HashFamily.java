package com.natsuki_kining.dsaaaij.source.code;

public interface HashFamily<AnyType>
{
    int hash(AnyType x, int which);
    int getNumberOfFunctions();
    void generateNewFunctions();
}

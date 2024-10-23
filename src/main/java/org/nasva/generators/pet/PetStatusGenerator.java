package org.nasva.generators.pet;

import org.nasva.models.PetStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PetStatusGenerator {
    public static PetStatus generateStatus(){
        List<PetStatus> list = Arrays.stream(PetStatus.values()).toList();
        return list.get(new Random().nextInt(list.size()));
    }
}

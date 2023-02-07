package com.rifa.Generics;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class BuyTicket {

    private String owner;

    private List<String> tickets;
}

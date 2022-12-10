package com.rifa.Service;

import com.rifa.Model.DTO.LuckyTicketDTO;
import com.rifa.Model.DTO.RaffleDTO;
import com.rifa.Model.LuckyTicket;
import com.rifa.Model.Raffle;
import com.rifa.Repository.ILuckyTicketRepository;
import com.rifa.Service.ServicesInterfaces.ILuckyTicketService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class LuckyTicketService implements ILuckyTicketService {

    @Autowired
    private ILuckyTicketRepository iLuckyTicketRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String createLuckyTicket(LuckyTicketDTO luckyTicketDTO)
    {
        try
        {
          int minimumTicketQuantity = 1;

          int quantityToBought = luckyTicketDTO.getQuantityBought();

          int count = 1;

          if(luckyTicketDTO.getQuantityBought() > 1)
          {
              while(count <= quantityToBought)
              {
                  LuckyTicketDTO luckyTickets = new LuckyTicketDTO();

                  luckyTickets.
                          setTicketOwner(luckyTicketDTO.getTicketOwner());

                  luckyTickets.
                          setQuantityBought(minimumTicketQuantity);

                  luckyTickets.
                          setLuckyNumber(generateAleatoryNumber());

                  luckyTickets.
                          setTicketPrice(luckyTicketDTO.getTicketPrice());

                  iLuckyTicketRepository.save(convertDtoToEntity(luckyTickets));

                  count++;


              }
          }
          else
          {
              iLuckyTicketRepository.save(convertDtoToEntity(luckyTicketDTO));
          }

          return "Your ticket was create";
        }
        catch (Exception e)
        {
            return "We cant create you Ticket";
        }
    }

    @Override
    public List<LuckyTicketDTO> getAllLuckyTickets() throws Exception
    {
        try
        {
            return iLuckyTicketRepository.findAll()
                    .stream()
                    .map(this::convertEntityToDto).
                    collect(Collectors.toList());
        }
        catch (Exception e)
        {
            throw new Exception("We cant return this List");
        }

    }

    @Override
    public String generateAleatoryNumber()
    {
        Integer max = 9;
        Integer sizeLuckyTicket = 6;

        String aleatoryNumber = "";

        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 1; i <= sizeLuckyTicket ; i++)
        {
            stringBuilder.append(random.nextInt(max));
        }

        aleatoryNumber = stringBuilder.toString();

        return aleatoryNumber;
    }

    private LuckyTicketDTO convertEntityToDto(LuckyTicket luckyTicket)
    {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);

        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        return modelMapper.map(luckyTicket, LuckyTicketDTO.class);
    }

    private LuckyTicket convertDtoToEntity(LuckyTicketDTO luckyTicketDTO)
    {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);

        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        return modelMapper.map(luckyTicketDTO, LuckyTicket.class);
    }

}

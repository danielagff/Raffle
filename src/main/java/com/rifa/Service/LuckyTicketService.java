package com.rifa.Service;

import com.rifa.Generics.BuyTicket;
import com.rifa.Model.DTO.LuckyTicketDTO;
import com.rifa.Model.LuckyTicket;
import com.rifa.Model.Raffle;
import com.rifa.Repository.ILuckyTicketRepository;
import com.rifa.Repository.IRaffleRepository;
import com.rifa.Service.ServicesInterfaces.ILuckyTicketService;
import lombok.SneakyThrows;
import net.bytebuddy.implementation.bytecode.Throw;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class LuckyTicketService implements ILuckyTicketService {

    @Autowired
    private ILuckyTicketRepository iLuckyTicketRepository;

    @Autowired
    private IRaffleRepository iRaffleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String createLuckyTicket(Long id)
    {
        try
        {
            Integer quantity = iRaffleRepository.getNumberTicketQuantityByIdRaffle(id);

            for(int i = 1; i <= quantity; i++)
            {
                String aleatoryNumber = generateAleatoryNumber();

                if(iLuckyTicketRepository.availableLuckyNumber(aleatoryNumber, id) == 0)
                {
                    LuckyTicketDTO ticket = new LuckyTicketDTO();

                    ticket.setLuckyNumber(aleatoryNumber);

                    ticket.setTicketPrice(iRaffleRepository.getPriceByIdRaffle(id));

                    ticket.setIdRaffle(id);

                    iLuckyTicketRepository.save(convertDtoToEntity(ticket));
                }
            }
            return "Created your numbers";
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
                    .map(this::convertEntityToDto)
                    .collect(Collectors.toList());
        }
        catch (Exception e)
        {
            throw new Exception("We cant return this List");
        }

    }

    @Override
    public @NotNull String generateAleatoryNumber()
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

    @Override
    public String getWinnerOfRaffle(Long id) {

        Random random = new Random();

        List<String> allLuckyTicketsByRaffle = iLuckyTicketRepository.getAllLuckyTicketsByRaffle(id);

        return allLuckyTicketsByRaffle.get(random.nextInt(allLuckyTicketsByRaffle.size()));
    }

    @Override
    public LuckyTicketDTO getWinnerByLuckyNumber(Long id) throws Exception {
        Raffle raffle = iRaffleRepository.getByIdRuffle(id);

        if(raffle.getRaffleStatus() == null && raffle.getWinnerTicket() == null)
        {
            String winnerLuckyNumber = getWinnerOfRaffle(id);

            LuckyTicket winnerInformationByLuckyNumber = iLuckyTicketRepository.getWinnerInformationByLuckyNumber(winnerLuckyNumber, id);

            raffle.setWinnerTicket(winnerInformationByLuckyNumber.getLuckyNumber());

            raffle.setRaffleStatus("CLOSED");

            iRaffleRepository.save(raffle);

            return convertEntityToDto(winnerInformationByLuckyNumber);
        }
        else
        {
            throw new Exception("This Raffle is CLOSED or already exist a Winner");
        }
    }

    @Override
    public List<LuckyTicketDTO> getAllParticipantsByRaffleId(Long id) {

        return iLuckyTicketRepository.getAllLuckyTicketByRaffleId(id)
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    @Override
    public BigDecimal getTotalPriceOfTicketsBought(BuyTicket buy, Long id) throws Exception {

            List<String> tickets = buy.getTickets();

            int quantity = 0;


        for(String ticket : tickets)
            {
                if(iLuckyTicketRepository.availableLuckyNumber(ticket,id) > 0 &&
                iLuckyTicketRepository.getTicketInformationByLuckyNumber(ticket, id).getTicketOwner() == null)
                {
                    LuckyTicket ticketObj = iLuckyTicketRepository.getTicketInformationByLuckyNumber(ticket, id);

                    ticketObj.setTicketOwner(buy.getOwner());

                    iLuckyTicketRepository.save(ticketObj);

                    quantity++;
                }
                else{
                    throw new Exception("One or more tickets was bought");
                }
            }
            BigDecimal ticketValue = iRaffleRepository.getPriceByIdRaffle(id);



            BigDecimal totalToPay = ticketValue.multiply((new BigDecimal(quantity)));

            return totalToPay;


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

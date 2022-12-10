package com.rifa.Service;

import com.rifa.Model.DTO.RaffleDTO;
import com.rifa.Model.Raffle;
import com.rifa.Repository.IRaffleRepository;
import com.rifa.Service.ServicesInterfaces.IRaffleService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaffleService implements IRaffleService {

     @Autowired
     private IRaffleRepository iRaffleRepository;

     @Autowired
     private ModelMapper modelMapper;

     @Override
     public List<RaffleDTO> findAllRaffles() throws Exception {
          try
          {
               return iRaffleRepository.findAll()
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
     public String createRaffles(RaffleDTO raffleDTO) {
          try
          {
               if(iRaffleRepository.isAvailableRaffleName(raffleDTO.getRaffleName()) >= 1)
               {
                    return "This raffle name is Unavailable";
               }
               else
               {

                    iRaffleRepository.save(modelMapper.map(raffleDTO, Raffle.class));
                    return "Raffle was saved";
               }


          }
          catch (Exception e)
          {
               return "We couldn't realize this operation";
          }

     }

     private RaffleDTO convertEntityToDto(Raffle raffle)
     {
          modelMapper.getConfiguration()
                  .setMatchingStrategy(MatchingStrategies.LOOSE);
         return modelMapper.map(raffle, RaffleDTO.class);
     }

     private Raffle convertDtoToEntity(RaffleDTO raffleDTO)
     {
          modelMapper.getConfiguration()
             .setMatchingStrategy(MatchingStrategies.LOOSE);
          return modelMapper.map(raffleDTO, Raffle.class);
     }




}

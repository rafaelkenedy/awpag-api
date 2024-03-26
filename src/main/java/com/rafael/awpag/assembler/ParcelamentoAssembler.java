package com.rafael.awpag.assembler;

import com.rafael.awpag.api.model.ParcelamentoModel;
import com.rafael.awpag.api.model.input.ParcelamentoInput;
import com.rafael.awpag.domain.model.Parcelamento;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ParcelamentoAssembler {

    private final ModelMapper modelMapper;

    public ParcelamentoModel toModel(Parcelamento parcelamento) {
        return modelMapper.map(parcelamento, ParcelamentoModel.class);
    }

    public List<ParcelamentoModel> toCollectionModel(List<Parcelamento> parcelamentos) {
        return parcelamentos.stream()
                .map(this::toModel)
                .toList();
    }

    public Parcelamento toEntity(ParcelamentoInput parcelamentoInput){
        return modelMapper.map(parcelamentoInput, Parcelamento.class);
    }
}
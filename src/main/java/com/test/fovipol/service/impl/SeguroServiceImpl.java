package com.test.fovipol.service.impl;

import com.test.fovipol.dao.SeguroDao;
import com.test.fovipol.dto.SeguroDto;
import com.test.fovipol.service.SeguroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeguroServiceImpl implements SeguroService {

    private final SeguroDao seguroDao;

    @Override
    @Transactional(readOnly = true)
    public List<SeguroDto> getSeguros() {
        return seguroDao.getSeguros();
    }

}

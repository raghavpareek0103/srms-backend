package com.srms.backend.service;

import com.srms.backend.entity.Result;
import java.util.List;

public interface ResultService {
    Result addResult(Result result);
    List<Result> getAllResults();
    Result getByStudent(Long studentId);
    Result updateResult(Long id, Result result);  // ← new
}
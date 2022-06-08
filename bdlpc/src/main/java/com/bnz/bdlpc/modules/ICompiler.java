package com.bnz.bdlpc.modules;

import com.bnz.shared.models.QuestionResponse;

public interface ICompiler {
    int compile(QuestionResponse questionResponse);
    String getResponse();
}

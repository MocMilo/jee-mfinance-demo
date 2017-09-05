package com.infoshare.core.analyzer.analyses;


import com.infoshare.core.analyzer.suggester.InputCriteriaSuggester;
import com.infoshare.core.model.MainContainer;

public abstract class Analysis {

    protected MainContainer mainContainer;
    protected InputCriteriaSuggester suggester = new InputCriteriaSuggester();

}

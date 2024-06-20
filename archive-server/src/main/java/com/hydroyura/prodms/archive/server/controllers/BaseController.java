package com.hydroyura.prodms.archive.server.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class BaseController {

    protected Logger LOG = LoggerFactory.getLogger(this.getClass());

    @ModelAttribute
    protected void requestLog(HttpServletRequest request) {
        LOG.info("Handle [{}] request to URL = [{}]", request.getMethod(), request.getRequestURL());
    }

}

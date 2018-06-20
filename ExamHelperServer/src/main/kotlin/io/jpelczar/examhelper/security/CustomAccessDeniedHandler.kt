package io.jpelczar.examhelper.security

import org.slf4j.LoggerFactory
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAccessDeniedHandler : AccessDeniedHandler {

    private val logger = LoggerFactory.getLogger(CustomAccessDeniedHandler::class.java)

    override fun handle(request: HttpServletRequest?, response: HttpServletResponse?, accessDeniedException: AccessDeniedException?) {
        val auth = SecurityContextHolder.getContext().authentication
        if (auth != null) {
            logger.info("User ${auth.name} attempted to access the protected URL: ${request?.requestURI}")
        }

        response?.sendRedirect("${request?.contextPath}/403")
    }
}
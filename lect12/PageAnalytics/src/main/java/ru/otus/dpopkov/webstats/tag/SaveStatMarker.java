package ru.otus.dpopkov.webstats.tag;

import ru.otus.dpopkov.webstats.model.StatMarker;
import ru.otus.dpopkov.webstats.util.JPASessionUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Date;

public class SaveStatMarker extends TagSupport {
    private static String markerName;
    static {
        markerName = System.getenv("MARKER_NAME");
        if (markerName == null) {
            markerName = StatMarker.DEFAULT_NAME;
        }
    }

    @Override
    public int doEndTag() throws JspException {
        StatMarker statMarker = new StatMarker();

        try {
            statMarker.setName(markerName);

            HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
            String uri = request.getRequestURI();
            String pageName = uri.substring(uri.lastIndexOf("/") + 1);
            statMarker.setPage(pageName);

            String remoteAddr = request.getRemoteAddr();
            statMarker.setRemoteAddr(remoteAddr);

            String userAgent = request.getHeader("user-agent");
            statMarker.setUserAgent(userAgent);

            // TODO: Добавить клиенсткое время обращения

            statMarker.setTimestamp(new Date());

            HttpSession session = pageContext.getSession();

            String username = (String) session.getAttribute("username");
            statMarker.setUsername(username);

            // TODO: Добавить куки
            // TODO: Добавить геолокацию

            Long previousId = (Long)session.getAttribute("previousStatMarkerId");
            statMarker.setPreviousId(previousId);

            save(statMarker);
            session.setAttribute("previousStatMarkerId", statMarker.getId());
        } catch (Exception e) {
            throw new JspException(e);
        }
        return Tag.EVAL_PAGE;
    }

    private void save(StatMarker statMarker) throws JspException {
        EntityManager em = JPASessionUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(statMarker);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new JspException(e);
        } finally {
            em.close();
        }
    }
}

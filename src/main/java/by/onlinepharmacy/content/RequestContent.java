package by.onlinepharmacy.content;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class RequestContent {
    private static Logger logger = LogManager.getLogger(RequestContent.class);

    private static final String CLOSE_SESSION = "sessionEnd";
    private HashMap<String, Object> requestAttributes;
    private Map<String, String[]> requestParameters;
    private HashMap<String, Object> sessionAttributes;
    private Collection<Part> requestParts;

    public RequestContent() {
        requestAttributes = new java.util.HashMap<>();
        requestParameters = new java.util.HashMap<>();
        sessionAttributes = new java.util.HashMap<>();
        requestParts = new java.util.ArrayList<>();
    }

    public void extractValues(HttpServletRequest request) {
        Enumeration<String> requestAttributeNames = request.getAttributeNames();
        while (requestAttributeNames.hasMoreElements()) {
            String name = requestAttributeNames.nextElement();
            requestAttributes.put(name, request.getAttribute(name));
        }
        requestParameters =  request.getParameterMap();


        try {
            requestParts = request.getParts();
        } catch (IOException e) {
            logger.error("Problem with get Collections.Part", e);
        } catch (ServletException e) {
            //  logger.error("Problem with get Collections.Part", e);
        }


        HttpSession session = request.getSession();
        Enumeration<String> sessionAttributeNames = session.getAttributeNames();
        while (sessionAttributeNames.hasMoreElements()) {
            String name = sessionAttributeNames.nextElement();
            sessionAttributes.put(name, session.getAttribute(name));
        }
    }

    public void insertAttributes(HttpServletRequest request, HttpSession session) {
        String sessionEnd = String.valueOf(getReuestAttribute("sessionEnd"));
        //close session
        if (CLOSE_SESSION.equals(sessionEnd)) {  // or if ( sessionEnd!=null) {
            session.invalidate();
            SessionAttributesClear();
        }

        requestAttributes.entrySet().forEach((entry) -> request.setAttribute(entry.getKey(), entry.getValue()));
        sessionAttributes.entrySet().forEach((entry) -> session.setAttribute(entry.getKey(), entry.getValue()));
    }

    public void SessionAttributesClear() {
        sessionAttributes.clear();
    }

    public HashMap<String, Object> getRequestAttributes() {
        return requestAttributes;
    }

    public void setRequestAttributes(HashMap<String, Object> requestAttributes) {

        this.requestAttributes = requestAttributes;
    }

    public void setRequestAttribute(String key, Object attribute) {

        this.requestAttributes.put(key, attribute);
    }

    public void setSessionAttribute(String key, Object attribute) {

        this.sessionAttributes.put(key, attribute);
    }

    public Map<String, String[]> getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(HashMap<String, String[]> requestParameters) {
        this.requestParameters = requestParameters;
    }

    public HashMap<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }

    public void setSessionAttributes(HashMap<String, Object> sessionAttributes) {
        this.sessionAttributes = sessionAttributes;
    }

    public String getRequestParameter(String name) {
        String[] values = requestParameters.get(name);
        String value = null;
        if (values != null) {
            value = values[0];
        }
        return value;
    }

    public String getSessionAttribute(String name) {
        String value = String.valueOf(sessionAttributes.get(name));

        return value;
    }


    public Object getReuestAttribute(String name) {
        Object value = requestAttributes.get(name);
        return value;
    }


    //get collection foto from form (type Part)
    public List<String> getRequestPartsName() {
        String STORAGE_FOLDER_PATH = "C:\\IdeaProjects\\OnlinePharmacy\\web\\resources\\image";
        List<String> list = new ArrayList<>();
        requestParts.stream().filter(part -> getFileName(part) != null).forEach(part1 -> list.add(getFileName(part1)));
        for (Part part : requestParts) {
            String name = this.getFileName(part);
            try {
                if (part != null && part.getSize() > 0
                        && part.getInputStream() != null && name != null) {

                    final Path outputFile = Paths.get(STORAGE_FOLDER_PATH, name);


                    try (final ReadableByteChannel input = Channels.newChannel(part
                            .getInputStream());
                         final WritableByteChannel output = Channels
                                 .newChannel(new FileOutputStream(outputFile
                                         .toFile()));) {
                        pipe(input, output);
                    }

                }
            } catch (IOException e) {
                logger.error("Problem with get getRequestPartsName", e);
            }
        }
        System.out.println(list.size());
        list.stream().forEach(System.out::println);
        return list;
    }

    //get file name from Part
    private String getFileName(final Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim()
                        .replace("\"", "");
            }
        }
        return null;
    }

    private static void pipe(ReadableByteChannel in, WritableByteChannel out)
            throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (in.read(buffer) >= 0 || buffer.position() > 0) {
            buffer.flip();
            out.write(buffer);
            buffer.compact();
        }
    }


}





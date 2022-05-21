package org.suai.lab16.controller;

import org.suai.lab16.repository.ListRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@WebServlet("/list")
public class ListController extends HttpServlet {
    private final ListRepository listRepository = new ListRepository();

    @Override
    public void init() {
        String initialList = "/home/nt/Projects/IdeaProjects/lab16/src/main/resources/initial-list.json";

        try (BufferedReader in = new BufferedReader(new FileReader(initialList))) {
            String content = in.lines().collect(Collectors.joining()).replaceAll("[{}\":,]", " ");
            StringTokenizer tokenizer = new StringTokenizer(content);

            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();

                String outer = token;
                List<String> inner = new ArrayList<>();

                token = tokenizer.nextToken();
                if (tokenizer.hasMoreTokens() && token.equals("[")) {
                    String word = tokenizer.nextToken();

                    while (!word.equals("]") && tokenizer.hasMoreTokens()) {
                        inner.add(word);
                        word = tokenizer.nextToken();
                    }
                }

                listRepository.add(outer, inner);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        Set<Map.Entry<String, List<String>>> list = listRepository.entrySet();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/resources/jsp/list.jsp").forward(request, response);
    }
}
package controller;

import model.Customer;
import service.CustomerService;
import service.CustomerServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    private CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String action = req.getParameter("action");
        action = action == null ? "" : action;
        switch (action) {
            case "delete":
                deleteCustomer(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            case "search":
                searchCustomer(req, resp);
                break;
            default:
                showAll(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        action = action == null ? "" : action;
        switch (action) {
            case "edit":
                editCustomer(req, resp);
                break;
            case "create":
                createCustomer(req, resp);
                break;
            default:
                showAll(req, resp);
        }
    }

    public void showAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("customerList", customerService.findAll());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/list.jsp");
        requestDispatcher.forward(req, resp);
    }

    public void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        customerService.delete(id);
        response.sendRedirect("/customer");
    }

    public void editCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        Customer customer = new Customer(id, name, email, address);
        System.out.println(id + "\t" + customer.getName());
        customerService.update(id, customer);
        response.sendRedirect("/customer");
    }

    public void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("editId", id);
        request.setAttribute("customerList", customerService.findAll());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/list.jsp");
        requestDispatcher.forward(request, response);
    }

    public void createCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        List<Customer> customerList = customerService.findAll();
        int id = customerList.get(customerList.size() - 1).getId() + 1;
        System.out.println(id + "\t" + name);
        customerService.create(new Customer(id, name, email, address));
        response.sendRedirect("/customer");
    }

    public void searchCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id;
        String requestId = request.getParameter("id");
        System.out.println("Request la: "+requestId);
        List<Customer> customerList = new ArrayList<>();
        if (requestId.equals("")) {
            customerList = customerService.findAll();
        } else {
            id = Integer.parseInt(requestId);
            Customer customer = customerService.findById(id);
            if (customer != null) {
                customerList.add(customer);
            } else {
                request.setAttribute("notFound", true);
                request.setAttribute("idSearch", request.getParameter("id"));
            }
        }
        request.setAttribute("customerList", customerList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/list.jsp");
        requestDispatcher.forward(request, response);
    }
}
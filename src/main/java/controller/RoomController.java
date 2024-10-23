package controller;

import model.Room;
import service.RoomService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.http.*;


@WebServlet("/room")
public class RoomController extends HttpServlet {
    private RoomService roomService = new RoomService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "search":
                showSearch(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            case "add":
                showAddForm(req, resp);
                break;
            case "delete":
                deleteRoom(req, resp);
                break;
            default:
                showHome(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "add":
                addRoom(req, resp);
                break;
            case "edit":
                editRoom(req, resp);
                break;
            case "delete":
                deleteRoom(req, resp);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/room?action=list");
                break;
        }
    }

    private void showSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        List<Room> rooms = roomService.findByString(keyword, keyword);
        req.setAttribute("rooms", rooms);
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }

    private void showHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Room> rooms = roomService.getAll();
        req.setAttribute("rooms", rooms);
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }

    private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("addRoom.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int roomId = Integer.parseInt(req.getParameter("roomId"));
        Room room = roomService.findById(roomId);
        req.setAttribute("room", room);
        req.getRequestDispatcher("editRoom.jsp").forward(req, resp);
    }

    private void addRoom(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String renterName = req.getParameter("renterName");
        String renterContact = req.getParameter("renterContact");
        Date moveInDate = Date.valueOf(req.getParameter("moveInDate"));
        int payCycle = Integer.parseInt(req.getParameter("payCycle"));
        String note = req.getParameter("note");

        Room room = new Room(0, renterName, renterContact, moveInDate, payCycle, note);

        if (roomService.add(room)) {
            resp.sendRedirect(req.getContextPath() + "/room?action=list");
        } else {
            req.setAttribute("message", "Error adding room");
            req.getRequestDispatcher("addRoom.jsp").forward(req, resp);
        }
    }

    private void editRoom(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int roomId = Integer.parseInt(req.getParameter("roomId"));
        String renterName = req.getParameter("renterName");
        String renterContact = req.getParameter("renterContact");
        Date moveInDate = Date.valueOf(req.getParameter("moveInDate"));
        int payCycle = Integer.parseInt(req.getParameter("payCycle"));
        String note = req.getParameter("note");

        Room room = new Room(roomId, renterName, renterContact, moveInDate, payCycle, note);

        if (roomService.edit(room, roomId)) {
            resp.sendRedirect(req.getContextPath() + "/room?action=list");
        } else {
            req.setAttribute("message", "Error editing room");
            req.getRequestDispatcher("editRoom.jsp").forward(req, resp);
        }
    }

    private void deleteRoom(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] roomIds = req.getParameterValues("roomIds");
        if (roomIds != null) {
            for (String roomId : roomIds) {
                roomService.delete(Integer.parseInt(roomId));
            }
        }
        resp.sendRedirect(req.getContextPath() + "/room?action=list");
    }
}



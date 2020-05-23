package sg.edu.np.mad.mad_recyclerview;

//Could be useful to add more than one class for an entire section of the assignment

import java.util.ArrayList;
import java.util.List;

public class TaskListModel {
    //Using a model in anticipation for week 6 database.
    public static class Task {
        private String taskDescription;
        private Boolean taskStatus = false; //Default state

        //Constructor
        public Task(String description) {
            this.taskDescription = description;
        }

        //For checking/ un-checking box
        public void TaskStatusUpdate(Boolean completed) {
            this.taskStatus = completed;
        }

        public String GetTaskDescription() {
            return (this.taskDescription);
        }

        public Boolean GetTaskStatus() {
            return (this.taskStatus);
        }
    }

    //Created a class so I don't have to keep recalling TaskListModel.Task
    public static class TaskList {

        private List<Task> taskList = new ArrayList<> ();

        //Initiate
        public TaskList() {}

        //Basic functions
        public void addTask(String description) { this.taskList.add(new Task(description)); }

        public void removeTask(int index) { this.taskList.remove(index); }

        public Task getTaskAtIndex(Integer index) { return this.taskList.get(index); }

        public Integer countTasks() { return this.taskList.size(); }
    }
}

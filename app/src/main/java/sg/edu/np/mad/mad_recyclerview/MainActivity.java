package sg.edu.np.mad.mad_recyclerview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //Declaring Variables
    private RecyclerView taskListRecyclerView;
    private EditText addTaskDescription;
    private Button addTaskButton;
    private TaskListAdapter taskListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Load previously saved data (From a database), in this case its hardcoded.
        TaskListModel.TaskList taskList = new TaskListModel.TaskList();
        taskList.addTask("Buy milk");
        taskList.addTask("Send postage");
        taskList.addTask("Buy Android development book");

        //Linking variables to their xml.
        addTaskDescription =  (EditText) findViewById(R.id.addTaskDescription);
        addTaskButton =  (Button) findViewById(R.id.addTaskButton);
        taskListRecyclerView = (RecyclerView) findViewById(R.id.taskListLayout);

        //Apply onclick listener to addTaskButton
        addTaskButton.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                //Save as variable so as to not have to re-type expression.
                String newTaskDescription = addTaskDescription.getText().toString();
                //Reset entered text
                addTaskDescription.setText("");

                //Basic validation
                if (newTaskDescription.length() > 0) {
                    //taskList variable is passed into the adapter in another section
                    taskListAdapter.taskList.addTask(newTaskDescription);
                    taskListAdapter.notifyDataSetChanged();
                    showNewEntry(taskListRecyclerView, taskListAdapter.taskList);

                }
            }
        });

        taskListAdapter = new TaskListAdapter(taskList);
        LinearLayoutManager taskLayoutManager = new LinearLayoutManager (this);

        taskListRecyclerView.setLayoutManager(taskLayoutManager);
        taskListRecyclerView.setItemAnimator(new DefaultItemAnimator());
        taskListRecyclerView.setAdapter(taskListAdapter);
        //Ignore this, this code was originally meant for the delete function through
        //click on the recycler view but it was for the entire row including the checkbox.
        //Leaving it here for reference.
        /**taskListRecyclerView.addOnItemTouchListener(
            new RecyclerTouchListener( this, taskListRecyclerView, new RecyclerTouchListener.RecyclerViewClickListener() {
                @Override
                public void onClick(View view, final int position) {
                    //Build Alert Dialog
                    String descOfSelectedItem = taskListAdapter.taskList.getTaskAtIndex(position).GetTaskDescription();
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("Delete");
                    builder.setMessage(Html.fromHtml(
                            "<div style='text-align: center'>Are you sure you want to delete<br /><b>" + descOfSelectedItem + "</b>?</div>"
                    ));
                    builder.setIcon(android.R.drawable.ic_menu_delete); //Search on this, its useful.
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            taskListAdapter.taskList.removeTask(position);
                            taskListAdapter.notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("No",null);

                    builder.create().show();
                }
            })
        );*/
    }

    /**
     * Upon calling this method, the keyboard will retract
     * and the recyclerview will scroll to the last item
     *
     * @param rv RecyclerView for scrolling to
     * @param taskList TaskList that was passed into RecyclerView //This was an annoying issue to fix.
     */
    private void showNewEntry(RecyclerView rv, TaskListModel.TaskList taskList){
        //scroll to the last item of the recyclerview
        rv.scrollToPosition(taskList.countTasks() - 1);

        //auto hide keyboard after entry
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(rv.getWindowToken(), 0);
    }

}

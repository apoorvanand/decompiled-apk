package bolts;

public class TaskCompletionSource<TResult> {
    private final Task<TResult> task = new Task<>();

    public Task<TResult> getTask() {
        return this.task;
    }

    public void setCancelled() {
        if (!trySetCancelled()) {
            throw new IllegalStateException("Cannot cancel a completed task.");
        }
    }

    public void setError(Exception exc) {
        if (!trySetError(exc)) {
            throw new IllegalStateException("Cannot set the error on a completed task.");
        }
    }

    public void setResult(TResult tresult) {
        if (!trySetResult(tresult)) {
            throw new IllegalStateException("Cannot set the result of a completed task.");
        }
    }

    public boolean trySetCancelled() {
        return this.task.a();
    }

    public boolean trySetError(Exception exc) {
        return this.task.a(exc);
    }

    public boolean trySetResult(TResult tresult) {
        return this.task.a(tresult);
    }
}
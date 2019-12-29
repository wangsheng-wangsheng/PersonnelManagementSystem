package domain.empower;

import java.util.Objects;

public class Role {
    private int id;
    private boolean checked;
    private String description;

    public Role(int id, boolean checked, String description) {
        this.id = id;
        this.checked = checked;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean isChecked() {
        return checked;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

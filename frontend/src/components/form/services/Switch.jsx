function Switch({ handleSwitchChange, checked, label_ }) {
    return (
        <div className="form-wrapper">
            <div className="form-check form-switch">
                <input
                    type="checkbox"
                    className="form-check-input"
                    id="customSwitches"
                    checked={checked}
                    onChange={handleSwitchChange}
                    readOnly
                />
                <label
                    className="form-check-label"
                    htmlFor="customSwitches"
                >
                    {label_}
                </label>
            </div>
        </div>
    );
}

export default Switch;

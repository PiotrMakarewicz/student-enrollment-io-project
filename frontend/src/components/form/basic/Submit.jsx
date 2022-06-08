/**
 * Input component without state.
 * 
 * 
 * @memberOf Form.Basic
 * @param   {function} onSubmit  Value of label above input.
 * @param   {string} value   Subbmit display value;
 * @param   {disable} type   If param equal true Submit is disable.
 * 
 * 
 * @example 
 *  <Submit 
        value={"Send form"} 
        onSubmit={onSubmit} 
    />
 */

function Submit({ onSubmit, value, disable, error, cl = "btn btn-primary" }) {
    const onSubbmitWraper = (e) => {
        e.preventDefault();
        onSubmit();
    };

    return (
        <div className="form-group center-content">
            <button
                type="submit"
                className={cl}
                onClick={onSubbmitWraper}
                disabled={disable}
            >
                {value}
            </button>
            <span className="error input-error">{error}</span>
        </div>
    );
}

export default Submit;

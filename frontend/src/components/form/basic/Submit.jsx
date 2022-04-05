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

function Submit({onSubmit, value, disable}) {

    const onSubbmitWraper = (e) => {
        e.preventDefault();
        onSubmit();
    }

    return (   
        <button 
            type="submit" 
            className="btn btn-primary" 
            onClick={onSubbmitWraper} 
            disabled={disable}
        >{value}</button>
    );
}

export default Submit;
function Switch({handleSwitchChange, checked, label_}) {
    return (  
    <div className='custom-control custom-switch'>
    <input
        type='checkbox'
        className='custom-control-input'
        id='customSwitches'
        checked={checked}
        onChange={handleSwitchChange}
        readOnly
    />
    <label className='custom-control-label' htmlFor='customSwitches'>
    {label_}
    </label>
</div> );
}

export default Switch;
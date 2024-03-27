import { MdFavorite } from 'react-icons/md';
import { NavLink } from 'react-router-dom';
import { FaLinkedin, FaSquareGithub } from 'react-icons/fa6';
import Logo from '../../assets/froggy-logo.svg';
import { links } from '../../routes/routes';

export default function Footer() {
    return (
        <div className='px-2 md:px-4 lg:px-16 xl:px-20 text-sm lg:text-normal xl:text-lg'>
            <hr />
            <div className='my-4 flex flex-col md:flex-row justify-between items-center gap-2'>
                <div className='flex flex-row items-center gap-2'>
                    <NavLink to={links.anchors[0].url} className='bg-light dark:bg-dark z-10 block w-fit hover:scale-105 duration-300'>
                        <img src={Logo} alt='Dabits Logo' className='h-[50px]' />
                    </NavLink>
                    <div className='flex gap-2 font-normal'>
                        <NavLink to={links.anchors[1].url} className='hover:text-primary'>{links.anchors[1].name}</NavLink>
                        <NavLink to={links.anchors[2].url} className='hover:text-primary'>{links.anchors[2].name}</NavLink>
                        <NavLink to={links.anchors[3].url} className='hover:text-primary'>{links.anchors[3].name}</NavLink>
                    </div>
                </div>
                <div className='flex gap-2'>
                    <p className='flex gap-1 items-center'>Hecho con <MdFavorite /> por Sheila Diz</p>
                    <div className="text-white text-3xl md:text-4xl flex">
                        <a href="https://github.com/sheidiz" target="_blank"> <FaSquareGithub /> </a>
                        <a href="https://www.linkedin.com/in/sheila-diz/" target="_blank"> <FaLinkedin /> </a>
                    </div>
                </div>
            </div>
        </div>
    )
}
